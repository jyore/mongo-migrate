
//Store library methods for later use :)

db.system.js.save({
	_id:"useDb",
	value: function (name){
		db = db.getMongo().getDB(name);
	}
});


