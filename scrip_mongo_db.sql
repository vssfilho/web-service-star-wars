use db_star_wars
db.createCollection("planeta");
db.createCollection("sequences");
db.sequences.insert({_id:"planeta",sequence_value:0})