# Chicago_Tires
This is an inventory system for a local well respected auto-repair and tire shop
in Iowa City, IA. The business is called Chicago Used and New Tires LLC. They
offer competitive prices for great used tires that are like new. 

From the end-user's perspective, this is a standalone desktop application thata can be 
ran on any machine. From the developer's perspective, the code base is written in 
Java and Swing design framework. To store inventory, the system leverages the SQLITE database.

There are advantages and disadvantages of the way I implemented this system. For advantages,
it can be run locally without needing to connect to the internet which means better security.
The disadvantage is that the system is set up so that one machine is running the program. Since the databse isn't connect to the internet which means
only 1 local access per user. If 2 users run it on 2 different machines, they will get different search results on different tire sizes since
the DB only updates local user and doesn't keep track of other users making insert and delete queries on it.

I am really proud of myself for making this system. I'm still testing it with the shop as this README file is written. Makes me truly appreciate technology :)
