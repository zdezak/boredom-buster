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
      FakeGetRandomActivity(isSuccessful = false),
      FakeIsActivitySaved(),
      FakeSaveActivity(),
      FakeDeleteActivity()
      )
       
       val expectedUiState = NewActivitiUiState.Success(activity1, false)
       
       coroutineRule.testDispatcher.scheduler.runCurrent()
       
       val actualState = viewModel.uiState.value
       assert(actualState, expectedUiState)
  }
  
  @Test
  fun 'creating viewModel updates ui state to error in case failure' (){
   val viewModel = ActivityViewModel(
    FakeGetRandomActivity(),
    FakeIsActivitySaved(),
    FakeSaveActivity(),
    FakeDeleteActivity()
    )
   coroutineRule.testDIspatcher.scheduler.runCurrent()
   val currentState = viewModel.uiState.value
   assert(currentState is NewsActivityUiState.Error)
  }
  
  @Test
  fun 'if activity is already saved, ui states isFavorite is set to true'(){
       val viewModel = ActivityViewModel(
    FakeGetRandomActivity(),
    FakeIsActivitySaved(),
    FakeSaveActivity(isActivity = true),
    FakeDeleteActivity()
    )
       val expectedUiState = NewsActivityUiState.Success(activity, true)
       coroutineRule.testDispatcher.scheduler.runCurrent()
       val actualState = viewModel.uiState.value
       assert(actualState, expectedUiState)
  }
  
  @Test
  fun 'calling loadNewActivity() updates ui state with new activity'(){
   val fakeGetRandomActivity = FakeGetRandomActivity()       
   val viewModel = ActivityViewModel(
          fakeGetRandomActivity,
          FakeIsActivitySaved(),
          FakeSaveActivity(),
          FakeDeleteActivity()
          )
          
          coroutineRule.testDispatcher.scheduler.runCurrent()
          val expectedUiState = NEwsActivityUiState.Success(activity2, false)
          fakeGetRandomActivity.activity = activity2
   viewModel.loadNewActivity()
   coroutineRule.testDispatcher.scheduler.runCurrent()
   val actualState = viewModel.uiState.value
   assert(actualState, expectedUiState)
}
