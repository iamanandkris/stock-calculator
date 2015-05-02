package bridge.user
import play.api.libs.json._

	
	case class Credential(handle:String, password:String,handle_type:String)
    object Credential{
       implicit val reads = Json.reads[Credential]
       implicit val writes = Json.writes[Credential]
    }
    
	case class Name(
		name_type: String,
	    title: String,
	    given: String,
	    family: String,
	    middle: String,
	    suffix:String,
	    patronymic_name:String,
	    orthography : String    
	)
	object Name{
       implicit val reads = Json.reads[Name]
       implicit val writes = Json.writes[Name]
    }
	
	case class Address(
		address_type: String,
		line1: String,
		line2: String,
		city: String,
		county: String,
		state : String,
		country_code: String,
		postal_code: String
	)
	object Address{
       implicit val reads = Json.reads[Address]
       implicit val writes = Json.writes[Address]
    }
	
	case class Phone(
	    country_code: String,
	    national_number: String,
	    extension: String,
	    phone_type: String
	)
	object Phone{
       implicit val reads = Json.reads[Phone]
       implicit val writes = Json.writes[Phone]
    }
	
	case class DateOfBirth(
		event_type:String,
		event_date:String
	)
	object DateOfBirth{
       implicit val reads = Json.reads[DateOfBirth]
       implicit val writes = Json.writes[DateOfBirth]
    }
	
	
	
	case class PersonDetails(
			primary_user:String,
			names: List[Name],
			addresses:List[Address]
			//phone_contacts:List[Phone],
			//date_of_birth:DateOfBirth,
			//country_code_of_nationality:String
	)
	object PersonDetails{
       implicit val reads = Json.reads[PersonDetails]
       implicit val writes = Json.writes[PersonDetails]
    }
	
	case class BusinessDetails(
			addresses:List[Address],
			phone_contacts:List[Phone],
			names: List[Name],
			business_type:String,
			category:String,
			sub_category:String,
			website_urls:List[String]
	)
	object BusinessDetails{
       implicit val reads = Json.reads[BusinessDetails]
       implicit val writes = Json.writes[BusinessDetails]
    }	