class FakeIsActivitySaved(
  private val isActivitySaved: Boolean = false
  ): IsActivitySaved{
  override suspend fun invoke(key:String): Boolean{
    return isActivitySaved
  }
}
