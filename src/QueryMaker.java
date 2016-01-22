

public class QueryMaker {

	
	public String query = "q=";
	public String format = "wt=xml";
	public String filters;
	public String rows;
	public String requestHandler = "/select?";
	public String cm = "%2C+";
	public String resultQuery;
	public String ident = "ident=true";
	
	
	public String frequencyQuery( String word, Boolean cleanDoc){
		
		//query to get word frequency
		/*String resultQuery = "/select?q="+word+"&rows=99999&fl=genes%3A"
			+word+"%2CDescriptorNameTTF%3A+totaltermfreq(medline_descriptor_name%2C"
			+word+")%2CArticleTitleTTF%3A+totaltermfreq(medline_article_title%2C"
			+word+")%2CJornalTitleTTF%3A+totaltermfreq(medline_journal_title%2C"
			+word+")%2CAbstracTTF%3A+totaltermfreq(medline_abstract_text%2C"
			+word+")%2Cid%2C+++DescriptorNameFreq%3Atermfreq(medline_descriptor_name%2C"
			+word+")%2C+++ArticleTitleFreq%3Atermfreq(medline_article_title%2C"
			+word+")%2C+++JornalTitleFreq%3Atermfreq(medline_journal_title%2C"
			+word+")%2C+++AbstractFreq%3A+termfreq(medline_abstract_text%2C"
			+word+")%2CFreqAlldocsAndFields%3A+sum(totaltermfreq(medline_abstract_text%2C"
			+word+")%2C++++sum(totaltermfreq(medline_journal_title%2C"
			+word+")%2C++++sum(totaltermfreq(medline_article_title%2C"
			+word+")%2C++++totaltermfreq(medline_descriptor_name%2C"
			+word+"))))&wt=xml&indent=true&facet=true&facet.field=medline_pub_year&hl=off&spellcheck=off&facet.limit=1000";
		*/
		
		//query to retrieve context
		String resultQuery = "/select?q="+word+"&start=0&rows=999999&wt=xml&indent=true&hl=true&hl.q="
					+word+"&hl.fragmenter&hl.fl=*&hl.snipppets=30&omitHeader=true&fl=id,medline_pub_type&&hl.simple.pre=&hl.simple.post=";
		
		
		
		if(cleanDoc){
			resultQuery.concat("&facet=off&hl=off&spellcheck=off");
		}
		return resultQuery;
	}
	
	
	
	public String completeQuery(String word, Boolean cleanDoc){
		
		//query to retrieve id,abstract and title from articles cointaining "gene"
		String resultQuery = "/select?q=medline_abstract_text%3A"
							+word+"+OR+medline_article_title%3A"+word
							+"&rows=999999&fl=id%2C+medline_article_title%2Cmedline_abstract_text%2Cmedline_journal_title"+"&wt=xml&indent=true";
		
		return resultQuery;
		
	}
	
}



