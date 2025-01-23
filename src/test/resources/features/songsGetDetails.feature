Feature: Obtener los detalles de una cancion

Scenario: Obtener los detalles de una cancion en base a un id
Given Tester desea buscar el detalle de una cancion en la api de shazam
When "Tester" utiliza el endpoint "get-details" con "1217912247"
Then Tester verifica que se retorna el detalle de la cancion

Scenario: Buscar una cancion en base a un id nulo
Given Tester desea buscar el detalle de una cancion en la api de shazam
When "Tester" utiliza el endpoint "get-details" con ""
Then Tester verifica que se retorna el detalle vacio