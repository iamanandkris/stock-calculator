package bridge.user
import play.api.libs.json._

case class PersonUser (
	user_type:String,
	language_code:String,
	credentials:List[Credential],
	person_details:PersonDetails
)

object PersonUser{
       implicit val reads = Json.reads[PersonUser]
       implicit val writes = Json.writes[PersonUser]
}

