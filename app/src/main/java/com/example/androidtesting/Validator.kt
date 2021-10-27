package com.example.androidtesting

//On cree un objet de validation
// des champs de l'utilisateur du
// fragment spend fragment


///Pour cree la class de test on fait click droit
// > generate > Test , et choisir Test et non AndroidTest vu que nous somme en UnitTest
object Validator {


    fun validateInput(amount:Int , description:String): Boolean {
            return !(amount<=0||description.isEmpty())
    }
}