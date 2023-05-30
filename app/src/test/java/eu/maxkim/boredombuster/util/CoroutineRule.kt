class CoroutineRule(
  val testDispatcher: TestDispatcher = StandartTestDispatcher()
): TestWatcher () {
  override fun starting (description: Description?){
    Dispatcher.setMain(testDispatcher)
  }
  
  override fun finished(description: Description?){
    Dispatcher.resetMain()
  }
}
