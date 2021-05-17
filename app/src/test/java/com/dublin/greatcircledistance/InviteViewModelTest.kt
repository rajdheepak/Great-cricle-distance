package com.dublin.greatcircledistance

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.dublin.greatcircledistance.domain.CustomerListRepository
import com.dublin.greatcircledistance.model.LCE
import com.dublin.greatcircledistance.viewmodel.InviteCustomerViewModel
import com.dublin.greatcircledistance.mockCustomerData.errorFlow
import com.dublin.greatcircledistance.mockCustomerData.expectedCustomersList
import com.dublin.greatcircledistance.mockCustomerData.goodFlow
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifySequence
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.dublin.greatcircledistance.mockCustomerData.sampleError

@ExperimentalCoroutinesApi
class InviteViewModelTest {

    private val mockRepository: CustomerListRepository = mockk()
    private val observer: Observer<LCE> = mockk(relaxed = true)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `reading customer data - success flow`() = runBlockingTest {
        every { mockRepository.getCustomers() }.returns(goodFlow)
        val inviteCustomerViewModel =
            InviteCustomerViewModel(mockRepository)
        inviteCustomerViewModel.customersWithinDistance.observeForever(observer)
        observer.apply {
            verifySequence {
                onChanged(LCE.Loading)
                onChanged(LCE.Content(expectedCustomersList()))
            }
        }
    }

    @Test
    fun `reading customer data - error flow`() = runBlockingTest {
        every { mockRepository.getCustomers() }.returns(errorFlow)
        val inviteCustomerViewModel =
            InviteCustomerViewModel(mockRepository)
        inviteCustomerViewModel.customersWithinDistance.observeForever(observer)
        observer.apply {
            verifySequence {
                onChanged(LCE.Loading)
                onChanged(LCE.Error(sampleError))
            }
        }
    }

}