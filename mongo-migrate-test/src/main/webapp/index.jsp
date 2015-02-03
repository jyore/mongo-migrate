<%@page import="com.jyore.mongo.test.MongoMigrateManager"%>

<%
	out.print("Starting to migrate...");
	MongoMigrateManager mgr = new MongoMigrateManager();
	mgr.doMigrate();
	out.print("done");
%>