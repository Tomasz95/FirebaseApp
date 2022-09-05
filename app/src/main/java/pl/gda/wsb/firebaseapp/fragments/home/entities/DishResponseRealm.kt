package pl.gda.wsb.firebaseapp.fragments.home.entities


import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class DishResponseRealm : RealmObject() {
    @PrimaryKey
   var id: String = ""

    //    val addresses: List<AddressesResponse>,
    var description: String = ""
    var ingredients: RealmList<String> = RealmList<String>()
    var name: String = ""
    var restaurant: String = ""
    var web: String = ""

}