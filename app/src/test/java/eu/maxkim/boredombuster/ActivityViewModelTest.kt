class ActivityViewModelTest(){
 private val testDispatcher = StandartTestDispatcher()
 @get:Rule
 val coroutineRule = CoroutineFule()
    
  @Test
  fun 'creating viewModel exposes loading ui state'(){
    val viewModel = ActivityViewModel(
      FakeGetRandomActivity(),
      FakeIsActivitySaved(),
      FakeSaveActivity(),
      FakeDeleteActivity()
      )
    
    assert(viewModel.uiState.value is NewActivityUiState.Loading)
  }
  
  @Test
  fun 'creating viewModel updates ui state to success after loading'(){
       val viewModel = ActivityViewModel(
      FakeGetRandomActivity(),
      FakeIsActivitySaved(),
      FakeSaveActivity(),
      FakeDeleteActivity()
      )
       
       val expectedUiState = NewActivitiUiState.Success(activity1, false)
       
       coroutineRule.testDispatcher.scheduler.runCurrent()
       
       val actualState = viewModel.uiState.value
       assert(actualState, expectedUiState)
  }
}
