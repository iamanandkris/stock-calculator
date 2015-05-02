package domain.user
import play.api.libs.json._

case class DomainCredential(handle:String, password:String,handle_type:String)
object DomainCredential{
  implicit val writes = Json.writes[DomainCredential]
  implicit val reads = Json.reads[DomainCredential]
}


	case class DomainName(
		name_type: String,
	    title: String,
	    given: String,
	    family: String,
	    middle: String,
	    suffix:String,
	    patronymic_name:String,
	    orthography : String    
	)
object DomainName{
  implicit val writes = Json.writes[DomainName]
  implicit val reads = Json.reads[DomainName]
}
	
	case class DomainAddress(
		address_type: String,
		line1: String,
		line2: String,
		city: String,
		county: String,
		state : String,
		country_code: String,
		postal_code: String
	)
object DomainAddress{
  implicit val writes = Json.writes[DomainAddress]
  implicit val reads = Json.reads[DomainAddress]
}
	
	
	case class DomainPhone(
	    country_code: String,
	    national_number: String,
	    extension: String,
	    phone_type: String
	)
object DomainPhone{
  implicit val writes = Json.writes[DomainPhone]
  implicit val reads = Json.reads[DomainPhone]
}
	
	
	case class DomainDateOfBirth(
		event_type:String,
		event_date:String
	)
object DomainDateOfBirth{
  implicit val writes = Json.writes[DomainDateOfBirth]
  implicit val reads = Json.reads[DomainDateOfBirth]
}

	case class DomainPersonDetails(
			primary_user:String,
			names: List[DomainName],
			addresses:List[DomainAddress]
			//phone_contacts:List[DomainPhone],
			//date_of_birth:DomainDateOfBirth,
			//country_code_of_nationality:String
	)
object DomainPersonDetails{
  implicit val writes = Json.writes[DomainPersonDetails]
  implicit val reads = Json.reads[DomainPersonDetails]
}
	
case class User(val id: Option[Long],
    user_type:String,
	language_code:String,
	credentials:List[DomainCredential],
	person_details:DomainPersonDetails
)
object User{
  implicit val writes = Json.writes[User]
  implicit val reads = Json.reads[User]
}	