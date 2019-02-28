set projectLocation=D:\Telia\Testing\Selenium
cd /d %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\lib\*
java org.testng.TestNG %projectLocation%\testng.xml
