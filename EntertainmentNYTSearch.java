import java.io.File;
import java.io.PrintStream;

import util.FileIO;

import com.temboo.Library.NYTimes.ArticleSearch.QueryArticles;
import com.temboo.Library.NYTimes.ArticleSearch.QueryArticles.QueryArticlesInputSet;
import com.temboo.Library.NYTimes.ArticleSearch.QueryArticles.QueryArticlesResultSet;
import com.temboo.core.TembooException;
import com.temboo.core.TembooSession;


public class EntertainmentNYTSearch
{
	public static void main(String[] args) throws TembooException
	{
		// Instantiate the Choreo, using a previously instantiated TembooSession object, eg:
		TembooSession session = new TembooSession("chessguy3", "myFirstApp", [TEMBOO API KEY]);
	
		QueryArticles queryArticlesChoreo = new QueryArticles(session);
	
		// Get an InputSet object for the choreo
		QueryArticlesInputSet queryArticlesInputs = queryArticlesChoreo.newInputSet();
	
		
		
		// Set inputs
		queryArticlesInputs.set_APIKey([NEW YORK TIMES API KEY]);
		
		// First one.
		//queryArticlesInputs.set_Query("Board Game");
		//queryArticlesInputs.set_Query("Game");
		//queryArticlesInputs.set_Query("Computer Game");
		//queryArticlesInputs.set_Query("Gambling");
		//queryArticlesInputs.set_Query("Sport");
		
		//queryArticlesInputs.set_Query("Fun");
		//queryArticlesInputs.set_Query("Arcade");
		//queryArticlesInputs.set_Query("Competition");
		//queryArticlesInputs.set_Query("Amusement");
		//queryArticlesInputs.set_Query("Entertainment");
		//queryArticlesInputs.set_Query("Movie");
		//queryArticlesInputs.set_Query("Drinking");
		//queryArticlesInputs.set_Query("Beach");
		//queryArticlesInputs.set_Query("Music");
		//queryArticlesInputs.set_Query("Reading");
		queryArticlesInputs.set_Query("Theater");
		
		// Execute Requests.
		// Note : The New York Times API only allows us to query the first 100 pages of 10 results each.
		for(int i = 0; i <= 100; i++)
		{
			
			File f = new File("NYT_Reading " + i + " .json");
			FileIO.openFile(f);
			PrintStream stream = FileIO.getStream(f);
			
			System.out.println("Query " + i);
			queryArticlesInputs.set_Offset("" + i);
			QueryArticlesResultSet queryArticlesResults = queryArticlesChoreo.execute(queryArticlesInputs);
			stream.println(queryArticlesResults.get_Response());
			
			// Delay query time.
			try {
			    Thread.sleep(100);                 //1000 milliseconds is one second.
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			
			// close the stream.
			FileIO.closeFile(f);
		}
	
		
		
		System.out.println("Done making requests.");
	}
	
}
