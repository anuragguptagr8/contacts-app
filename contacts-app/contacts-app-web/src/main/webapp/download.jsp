<!-- mess ( -->
<!-- but I don't have access to the response object in a controller -->

<%@ page import="java.io.*"%>

<%
	File file = (File) request.getAttribute("file");
    String fileName = (String) request.getAttribute("fileName");
    
    InputStream inputStream = new FileInputStream(file);
	ServletContext context = request.getServletContext();
	String mimeType = context.getMimeType(file.getAbsolutePath());
	response.setContentType(mimeType != null ? mimeType
	     : "application/octet-stream");
	response.setContentLength((int) file.length());
	response.setHeader("Content-Disposition", "attachment; filename=\""
	     + fileName + "\"");
	
	ServletOutputStream os = response.getOutputStream();
	byte[] bufferData = new byte[1024];
	int read = 0;
	while ((read = inputStream.read(bufferData)) != -1) {
		os.write(bufferData, 0, read);
	}
	os.flush();
	os.close();
	inputStream.close();
%>
