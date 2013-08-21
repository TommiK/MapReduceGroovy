package system


class User {
	def visitedPlaces=[]
	def id
	
	User(id,visitedPlaces){
		this.id=id
		parse(visitedPlaces);
	}
	
	String toString(){
		String answer="$id"+visitedPlaces[0];
		for(place in visitedPlaces){
				answer=answer+","+place
		}
		return answer
	}
	
	void parse(String s){
		
		def strings=s.split(",")
		
		strings.each{ this.visitedPlaces << it.toInteger()}
		
		
	}
	
}
