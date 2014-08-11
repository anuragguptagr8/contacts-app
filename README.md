contacts-app
============

Contacts Manager Web App for iTechArt Java Training Courses

Set *'URIEncoding="UTF-8"'* in tomcat connector at ***(tomcat_dir)*/conf/server.xml** like here:

`<Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" URIEncoding="UTF-8" />`

Db configuration is stored at contactsapp-dal resources folders (*'src/main/resources'* and *'src/test/resources'*) in **'db.properties'** file.

You should specify your own path to log-file in **'log4j.properties'** at *'contactsapp-web/src/main/resources'*

Don't forget to set deafult paths for attachments and photos in **'web.properties'** at *'contactsapp-web/src/main/resources'*
