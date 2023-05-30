class FakeSaveActivity (): SaveActivity{
  var wasCalled = false
    private set
  override suspend fun invoke(activity: Activity){
    wasCalled = true
  }
}
