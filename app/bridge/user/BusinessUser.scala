package bridge.user
import play.api.libs.json._

case class BusinessUser (
	user_type:String,
	business_details:BusinessDetails
)

object BusinessUser{
       implicit val reads = Json.reads[BusinessUser]
       implicit val writes = Json.writes[BusinessUser]
}
