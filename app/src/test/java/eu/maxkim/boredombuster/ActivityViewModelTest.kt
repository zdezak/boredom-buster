package eu.maxkim.boredombuster

import CoroutineRule
import FakeDeleteActivity
import FakeGetRandomActivity
import FakeIsActivitySaved
import FakeSaveActivity
import activity1
import activity2
import eu.maxkim.boredombuster.activity.ui.newactivity.NewActivityUiState
import eu.maxkim.boredombuster.activity.ui.newactivity.NewActivityViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ActivityViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    @Test
    fun `creating a viewModel exposes loading ui state`() {
        val viewModel = NewActivityViewModel(
            FakeGetRandomActivity(),
            FakeSaveActivity(),
            FakeDeleteActivity(),
            FakeIsActivitySaved()
        )

        assert(viewModel.uiState.value is NewActivityUiState.Loading)
    }

    @Test
    fun `creating viewModel updates ui state to success after loading`() {
        val viewModel = NewActivityViewModel(
            FakeGetRandomActivity(),
            FakeSaveActivity(),
            FakeDeleteActivity(),
            FakeIsActivitySaved()
        )

        val expectedUiState = NewActivityUiState.Success(activity1, false)

        coroutineRule.testDispatcher.scheduler.runCurrent()

        val actualState = viewModel.uiState.value
        assertEquals(actualState, expectedUiState)
    }

    @Test
    fun `creating viewModel updates ui state to error in case failure`() {
        val viewModel = NewActivityViewModel(
            FakeGetRandomActivity(isSuccessful = false),
            FakeSaveActivity(),
            FakeDeleteActivity(),
            FakeIsActivitySaved()
        )
        coroutineRule.testDispatcher.scheduler.runCurrent()
        val currentState = viewModel.uiState.value
        assert(currentState is NewActivityUiState.Error)
    }

    @Test
    fun `if activity is already saved, ui states isFavorite is set to true`() {
        val viewModel = NewActivityViewModel(
            FakeGetRandomActivity(),
            FakeSaveActivity(),
            FakeDeleteActivity(),
            FakeIsActivitySaved(isActivitySaved = true),
        )
        val expectedUiState = NewActivityUiState.Success(activity1, true)
        coroutineRule.testDispatcher.scheduler.runCurrent()
        val actualState = viewModel.uiState.value
        assertEquals(actualState, expectedUiState)
    }

    @Test
    fun `calling loadNewActivity() updates ui state with new activity`() {
        val fakeGetRandomActivity = FakeGetRandomActivity()
        val viewModel = NewActivityViewModel(
            fakeGetRandomActivity,
            FakeSaveActivity(),
            FakeDeleteActivity(),
            FakeIsActivitySaved()
        )

        coroutineRule.testDispatcher.scheduler.runCurrent()
        val expectedUiState = NewActivityUiState.Success(activity2, false)
        fakeGetRandomActivity.activity = activity2
        viewModel.loadNewActivity()
        coroutineRule.testDispatcher.scheduler.runCurrent()
        val actualState = viewModel.uiState.value
        assertEquals(actualState, expectedUiState)
    }
}
