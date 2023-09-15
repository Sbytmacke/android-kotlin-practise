# ViewModel

Mediante el ViewModel se comparten los datos entre fragmentos/secciones, por tanto cada
fragmento tiene su propio ViewModel, mediante el observador LiveData() notifican a los ViewModel
interesados en cambios de otros fragmentos.

Los fragmentos siempre están observando cambios en el ViewModel para actualizar la interfaz
