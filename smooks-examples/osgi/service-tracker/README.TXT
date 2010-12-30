About This Example:
===================
    This examles demonstrates basic usage of Smooks in an OSGI environment. A bundle activator is used to drive the demo which
    will use a ServiceTracker to retrieve a SmooksOSGIFactory from the OSGI enviroment which is then uses to create a Smooks
    instance for filtering. For configuring Smooks the 'smooks-config.xml' is in this bundle. The input for filtering, the 
    'input-message.xml' file, is also packaged inside the bundle.

	The following features are demonstrated:
	* Using OSGI's ServiceTracker to retrieve a SmooksOSGIFactory to create the Smooks instance.

	See smooks-config.xml for inline comments.

    See:
        1. The "MainActivator" class in src/main/java/example/activator/MainActivator.java.
        2. The input message in input-message.xml.
        3. smooks-config.xml.

How to Run?
===========
    Requirements:
        1. JDK 1.5
        2. Maven 3.x (http://maven.apache.org/download.html)
           or 
           Gradle 1.0.x (http://gradle.org/downloads.html)


    Building with Maven:
        1. "mvn clean install"

    Building with Gradle:
        1. "gradle clean install"

    Running:
        1. Install and start smooks-all bundle into your target OSGi container. 
        2. Install this examples bundle jar into you target container
        3. Start the example bundle.
