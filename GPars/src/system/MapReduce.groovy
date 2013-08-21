package system

import groovyx.gpars.GParsPool


def sequenceA=""
def sequenceB=""
def sequenceC=""
new File("SequenceA.txt").eachLine { line -> sequenceA+=line }
new File("SequenceB.txt").eachLine { line -> sequenceB+=line }
new File("SequenceC.txt").eachLine { line -> sequenceC+=line }


def userlist=[]
def startcreate=System.currentTimeMillis();
createUser(userlist,10,sequenceA)
def createUser(userlist,number,sequence){
	number.times{userlist << new User("id",sequence)}
}

def mapreduce(arg){
	GParsPool.withPool{
		return arg.parallel.map {
			it.visitedPlaces.groupBy({it}).collectEntries { key, value -> [key, value.size()]}
		}.
		reduce { a, b ->
			// merge two maps by adding their values (solution from Stackoverflow)
			def newMap = [a, b]*.keySet().flatten().unique().collectEntries {

				[ (it): [a, b]*.get( it ).findAll().sum() ]}
		}
	}
}

def start=System.currentTimeMillis();
//println mapreduce(userlist)
mapreduce(userlist)
println "reuqired time:"+(System.currentTimeMillis()-start);




