package com.example.kotlintesting.facts.viewmodel

import android.content.Context
import androidx.test.InstrumentationRegistry.getTargetContext
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.example.kotlintesting.api.MockResponseFileReader
import com.example.kotlintesting.api.Webservice
import com.example.kotlintesting.facts.repository.FactsRepository
import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runner.manipulation.Ordering
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class MockWebUnitTests {

    private val server: MockWebServer = MockWebServer()
    private val MOCK_WEBSERVER_PORT = 8000

    lateinit var placeholderApi: Webservice
    lateinit var jsonRepository: FactsRepository


    @Before
    fun init() {

//        context = getInstrumentation().context
        server.start(MOCK_WEBSERVER_PORT)

        placeholderApi = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(Webservice::class.java)

        jsonRepository = FactsRepository(null, placeholderApi)
    }

    @After
    fun shutdown() {
        server.shutdown()
    }

    @Test
    fun `JsonPlaceholder APIs parse correctly`() {


        server.apply {
            enqueue(MockResponse().setBody(MockResponseFileReader("jsonplaceholder_success.json").content))
        }
        jsonRepository.getFactsData()
            .test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertComplete()
            .assertValueCount(1)
            .assertValue { it.rows.size ==14 }
            .assertNoErrors()
    }

}