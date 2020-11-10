*************************************************************Contar filas visibles
***Por ello para contar solo las filas visibles se necesitaría emplear la fórmula:
	=SUBTOTALES(103,D4:D8)

***Por tanto para contar todas las filas se necesitaría emplear la fórmula:
	=SUBTOTALES(3,D4:D8)

***Contar filas ocultas
	=CONTARA(D4:D8) -SUBTOTALES(103,D4:D8)
