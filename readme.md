## Git Rules

* **Aldrig push til masterbranch medmindre programmet virker og er godkendt af releasemanageren**
* Gør brug af **branches** til at kode og teste uden at påvirke masterbranch. **Mere om git og branches:** https://www.jetbrains.com/help/idea/2016.2/managing-branches.html
* Ved **conflicts** f.eks. 2 personer har ændret i samme dokument, find den anden person og bliv enig om hvad som skal beholdes. 
* Ved oprettelse af nye branches kald da den nye branch dit navn. 
* Brug funktionen **"Issues"** til at oprette og løse problemer/bugs. 


##Installation og download
Follow this tutorial to get it up and running on your own computer.

1. Follow this guide: https://www.jetbrains.com/help/idea/2016.2/creating-and-running-your-first-restful-web-service-on-glassfish-application-server.html, to set up your IntelliJ correctly so it's ready for this repository. After you have followed the above guide step-by-step move on with step no. 2. 
2. Clone this repository into a folder of your choice.
3. Open up IntelliJ and create new project.
4. Select Java Enterprise and apply following settings:
    - Project SDK: You shouldn't have to change this. It should be your java version by default.
    - Java EE version: Like above. If not select Java EE 7.
    - Application server: If GlassFish isn't selected and not available select "new". Choose GlassFish server. Navigate to where you saved your GlassFish folder in "Configuring the GlassFish server in IntelliJ IDEA" in the guide in step number 1.
    - Additional Libraries and Frameworks: select 'Web Application' and 'RESTful Web Service'
    - Select 'Download' in the bottom
5. Click next and next.
6. Navigate to the folder, where you cloned this repository.
7. Click 'Finish'
You application will not run right now, because of a missing Gson library.
8. Open your file manager (ex finder) and place your Gson.jar in the projects lib-folder. If you don't have it, download Gson.
9. go to 'File' → 'Project Structure' → 'Libraries'. Add new Library. Chose Java. Navigate to /lib/Gson.jar
10. Still in 'Project Structure' select 'Problems' on the left hand side. Fix both of them by clicking 'Fix' and select top option. 
11. If you've done everything correctly, your server should be able to run. Wait a little bit of time until the artifact is deplayed, and the server should open in your default browser by it self. Navigate to localhost:8080/yourProject/helloworld to see if it works. 
12. Play araound with the source-code to get a feel of Jersey. 

    
