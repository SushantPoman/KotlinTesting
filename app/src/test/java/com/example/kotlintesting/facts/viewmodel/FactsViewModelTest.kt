package com.example.kotlintesting.facts.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.kotlintesting.global.RepositoryFactory
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import java.time.Instant

@RunWith(MockitoJUnitRunner::class)
class FactsViewModelTest {

//    A JUnit Test Rule that swaps the background executor used by
//    the architecture components with a different one which executes each task synchronously.
//    You can use this rule for your host side tests that use Architecture components.
    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

//    Test rule for making the RxJava to run synchronously in unit test
    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Mock
    private lateinit var countryViewModel: FactsViewModel
    val context = mock(Context::class.java)

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        countryViewModel = FactsViewModel(RepositoryFactory.createFactsRepository(context = context))
    }

    @Test
    fun getFactsInfo_onSuccess(){
        countryViewModel.getFactsData()
        val countryViewModels = countryViewModel.factsModel.value
        assertEquals(countryViewModels?.rows?.get(0)?.title, "Beavers")
    }

}