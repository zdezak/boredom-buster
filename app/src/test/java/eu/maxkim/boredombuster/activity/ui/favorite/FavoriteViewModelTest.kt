import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest(){
  
  @get:Rule
  val instantExecutorRule = InstantTaskExecutorRule()
  
  private val mockGetFavoriteActivities: GetFavoriteActivities = mock()
  private val mockDeleteActivity: DeleteActivity = mock()    
  private val activityListObserver: Observer<FavoritesUIState> = mock()
  
  inline fun <T> whenever(methodCall: T): OngoingStavving<T>
  infix fun <T> OngoingStabbing<T>.doReturn(t:T): OngoingStubbing<T>
   @Captor
   private lateinit var activityListCaptor: ArgumentCaptor<FavoritsUiState>
    
  
  @Test
  fun `the view model maps list of activities to list ui state`(){
    val liveDateToReturn = MutableLiveData<List<Activity>>()
      .apply{value = listOf(activity1,activity2)}
    val expectedList = listOf(activity1,activity2)

    
    whenever(mockGetFavoriteActivities.invoke()).doReturn(liveDataToReturn)
    
    val viewModel = FavoriteViewModel(mockGetFavoriteActivities, mockDelateActivity)
    
    viewModel.favoriteActivityLiveData.observeForever(activityListObserver)
    
    verify(activityListObserver,times(1)).onChanged(activityListCaptor.capture())
    assert(activityListCaptor.value is FavoritesUIState.List)
    
    val actualList = (activityListCaptor.value as FavoritesUiState.List).activityList)
    assertEquals(actualList, expectedList)
  }
}
