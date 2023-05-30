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
}
