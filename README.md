Project : 
  A simple application displaying data from three parallel network requests and displaying data in the mainactivity.

Libraries used - 
   Retrofit2 for networking
   kotlin couroutines - to fetch data in background thread
   
Project Structure 
   
ui - MainActivity : Observes the data from ViewModel through observer and displays it
     MainViewModel : Sends request to data layer to get data and feeds to mainactivity through livedata
	 MainViewModelFactory : Factory class to create MainViewModel with arguments
network - Network Service: makes network calls and gives responses
          Networking : returns networkService object 
		  NetworkHelper : checks connectivity 

Data - DataSource : Gets response using networkService
       DataRepository : feeds data after processing to ViewModel
       Result: Formatting and returning responses to ViewModel
	   
Design Patterns Used

MVVM - app architecture design pattern.

Dependency Injection pattern: Provided dependencies for MainViewModel and DataRepository from oustide the class 
Facade Pattern: ViewModel calls DataRepository for data, DataRepository internally uses datasource which uses networking layer to fetch data
Singleton : Networking provides single retrofit instance  for whole application.
Factory Design Pattern : Creating viewmodel using viewmodelfactory class which gives the same viewmodel even when the activity/fragment is destroyed

Code Optimization : Used ktlint for code format and typos in code

APK reduction : Enabled R8 and progaurd