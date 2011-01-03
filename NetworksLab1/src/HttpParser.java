import java.util.*;


public abstract class HttpParser {

	
	public final static String CRLF = "\r\n";
	
	private static final Tracer tracer = Tracer.getTracerForThisClass();
	
	private ArrayList<HttpHeader> m_Headers;
	private byte[] m_Content;
	private HttpVersion m_HttpVersion;
	
	/**
	 * Creates a new Parser object
	 */
	public HttpParser()
	{
		tracer.TraceToConsole("Starting HTTP Parser");
		
		m_Headers = new ArrayList<HttpHeader>();
	}
	
	/**
	 * Adds an header to this parser
	 * @param header The header to add
	 */
	public void AddHeader(HttpHeader header)
	{
		if (header != null)
		{
			m_Headers.add(header);
		}
	}

	/**
	 * Returns the headers associated to this parser
	 * @return
	 */
	public ArrayList<HttpHeader> GetHeaders()
	{
		return new ArrayList<HttpHeader>(m_Headers);
	}
	
	/**
	 * Sets the content to this parser
	 * @param data
	 */
	public void SetContent(byte[] data)
	{
		m_Content = data;
	}
	
	/**
	 * 
	 * @return The content of the current parser or null if empty
	 */
	public byte[] GetContent()
	{
		return m_Content;
	}
	
	/**
	 * 
	 * @return The content size
	 */
	public int GetContentSize()
	{
		int contentSize = 0;
		
		if (m_Content != null)
		{
			contentSize = m_Content.length;
		}
		
		return contentSize;
	}
	
	/**
	 * 
	 * @return The HTTP version for this request (1.0 or 1.1)
	 */
	public HttpVersion GetHttpVersion()
	{
		return m_HttpVersion;
	}
	
	/**
	 * Sets the HTTP version for this request
	 * @param version the version
	 */
	public void SetHttpVersion(HttpVersion version)
	{
		m_HttpVersion = version;
	}
	
	/**
	 * 
	 * @return The parser header as a string
	 */
	abstract String HeaderString();
}
