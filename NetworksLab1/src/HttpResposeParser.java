
public class HttpResposeParser extends HttpParser {

	private HttpResponseCode m_ResponseCode;
	
	/**
	 * Create a HTTP response
	 * @param code The response code
	 */
	public HttpResposeParser(HttpResponseCode code)
	{
		m_ResponseCode = code;
	}
	
	/**
	 * If response is “HTTP/1.0 200 OK”, the response code is 200
	 * @return Returns the response code of this response 
	 */
	public String GetHttpResponseCode()
	{
		return m_ResponseCode.GetCode();
	}
	
	/**
	 * If response is “HTTP/1.0 200 OK”, the response description is OK
	 * @return Returns the descriptions of the current response code
	 */
	public String GetHttpResponseDescription()
	{
		return m_ResponseCode.GetDescription();
	}

	@Override
	String HeaderString() {
		StringBuilder sb = new StringBuilder();

		for (HttpHeader currentHeader : GetHeaders())
		{
			sb.append(currentHeader.toString());
			sb.append(CRLF);
		}
		return sb.toString();
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		// output the status line
		// Status-Line = HTTP-Version SP Status-Code SP Reason-Phrase CRLF
		sb.append(GetHttpVersion().GetHTTPVersion());
		sb.append(" ");
		sb.append(m_ResponseCode);
		sb.append(CRLF);
		
		// output the headers
		sb.append(HeaderString());
		sb.append(CRLF);
		
		if (GetContentSize() > 0)
		{
			sb.append(new String(GetContent()));
		}
		
		return sb.toString();
	}
}
