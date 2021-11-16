package com.servicepoller;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.*;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

@Path("/index")
public class UIResouce {
    final static Logger logger = LoggerFactory.getLogger(ServiceUrlResouce.class);

    @GET
    @Produces({MediaType.TEXT_HTML})
    public String index() {
        return "<html>\n"
        		+ "<head>\n"
        		+ "<meta http-equiv=\"refresh\" content=\"10\">\n"
        		+ "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\">    </script>\n"
        		+ "  <style>\n"
        		+ "table, td, th {\n"
        		+ "border: 1px solid black;\n"
        		+ "}\n"
        		+ "\n"
        		+ "table {\n"
        		+ "    border-collapse: collapse;\n"
        		+ "    width: 100%;\n"
        		+ "}\n"
        		+ "\n"
        		+ "td {\n"
        		+ "    height: 50px;\n"
        		+ "    vertical-align: bottom;\n"
        		+ "}\n"
        		+ "</style>\n"
        		+ "</head>\n"
        		+ " <body>\n"
        		+ "  <table id='mytable'>\n"
        		+ "<tr>\n"
        		+ "<th>id</th>\n"
        		+ "<th>name</th>\n"
        		+ "<th>category</th>\n"
        		+ "<th>state</th>\n"
        		+ "  </tr>\n"
        		+ "<div>\n"
        		+ "</div>\n"
        		+ "\n"
        		+ "  </table>\n"
        		+ "<script>\n"
        		+ "\n"
        		+ "$(document).ready(function(){\n"
        		+ "var url  = \"http://localhost:8080/service/url\";\n"
        		+ "$.getJSON(url, function( data ) {\n"
        		+ "	console.log(data);\n"
        		+ "    var obj = data;\n"
        		+ "    for(var i=0;i<obj.length;i++)\n"
        		+ "    {\n"
        		+ "        var tr  =\"<tr>\"+\n"
        		+ "                \"<td>\"+obj[i][\"id\"]+\"</td>\"+\n"
        		+ "                \"<td>\"+obj[i][\"name\"]+\"</td>\"+\n"
        		+ "                \"<td>\"+obj[i][\"url\"]+\"</td>\"+\n"
        		+ "                \"<td>\"+obj[i][\"status\"]+\"</td></tr>\";\n"
        		+ "       $(\"#mytable\").append(tr);\n"
        		+ "    }\n"
        		+ "});\n"
        		+ "}); \n"
        		+ "\n"
        		+ "</script>\n"
        		+ "</body>\n"
        		+ "</html>\n"
        		+ "\n"
        		+ "";
    }
}
