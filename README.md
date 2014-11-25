WebService
----------

### Crearte web application with netbeans

1. Choose File > New Project. Select web application from Java web
2. Type FlowerAlbumService in the Project Name file. Select the location you want for project. Keep the default setting for the other option and click Next. The Server and setting page opens.
3. Select the GlassFish server and Java EE version 6 or Java EE 7 Web.
4. Click finish.
5. Right-click the Source Packages node and select New > Java Package.
Alternatively, select New > Other and select Java Package in the Java category of the New File wizard
6. Set Name the pagekage.

### Adding the Web Service

1. Right-click the FlowerAlbumService project and choose New > Web Service. Alternatively, choose New > Other and then select Web Service under Web Services in the New File wizard. The New Web Service wizard opens.
2. In the New Web Service wizard, type FlowerService in Web Service Name and org.flower.service in Package Name. Select Create Web Service from Scratch. Select Implement Service as Stateless Session Bean. Click Finish.
3. Open FlowerService.java in the editor.

### Testing the Web Service

1. Right-click the FlowerAlbumService node and select Deploy.
2. Expand the project's Web Services node. Right-click the FlowerService and select Test Web Service. 
3. The web service tester opens in your browser. Type "image_name" in the getFlower parameter field.
4. Select button getFlower.

Tutorial: SOAP Web Services in https://netbeans.org/kb/trails/web.html

==========
