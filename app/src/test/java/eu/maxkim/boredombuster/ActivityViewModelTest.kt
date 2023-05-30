class ActivityViewModelTest(){
 private val testDispatcher = StandartTestDispatcher()
  
  @Before
  fun setUp(){
    Dispatcher.setMain(testDispatcher)
  }
  
  @After
  fun tearDown(){
    Dispatcher.resetMain()
  }
  
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
