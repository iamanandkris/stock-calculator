package controllers.user

import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import services.user.UserServiceComponent
import bridge.user._
import domain.user._




trait UserController extends Controller {
    self: UserServiceComponent =>
    
    //implicit val userReads = ((__ \ "email").read[String] and (__ \ "user").read[String])(UserResource.apply _)
    implicit val userReads = Json.reads[PersonUser]
    
    /*implicit val userWrites = new Writes[User] {
        override def writes(user: User): JsValue = {
            Json.obj(
                "id" -> user.id,
                "email" -> user.email,
                "user" -> user.user
            )
        }
    }*/
    
    implicit val userWrites = Json.writes[PersonUser]
    
                                           
    def createUser = Action(parse.json) {request =>
        unmarshalUserResource(request, (resource: PersonUser) => {
            val credList = resource.credentials
        	val credentialObj = credList.map(credent => {
        	  DomainCredential(credent.handle,credent.password,credent.handle_type)
        	})
        	
        	val persDetails = resource.person_details
        	val personDomainDetails = DomainPersonDetails(persDetails.primary_user,persDetails.names.map(prNames => {
        					DomainName( prNames.name_type,
										prNames.title,
										prNames.given,
										prNames.family,
										prNames.middle,
										prNames.suffix,
										prNames.patronymic_name,
										prNames.orthography)
        					}  
	        			),
	        			persDetails.addresses.map(prAddress => {
	        				DomainAddress(prAddress.address_type,
										prAddress.line1,
										prAddress.line2,
										prAddress.city,
										prAddress.county,
										prAddress.state ,
										prAddress.country_code,
										prAddress.postal_code)
	        				}
	        			)
        			)
        	
            val addressObject = DomainAddress("test","test","test","test","test","test","test","test")
            val phoneObject = DomainPhone("test","test","test","test")
            val dobObject = DomainDateOfBirth("test","test")
            
            val user = User(Option.empty,
                resource.user_type,
                resource.language_code,
                credentialObj,
                personDomainDetails
                )
                //List(credentialObj),
                //personDetailObjec)
                
            userService.createUser(user)
            Created
        })
    }
    
    def updateUser(id: Long) = Action(parse.json) {request =>
        unmarshalUserResource(request, (resource: PersonUser) => {
          
        	val credList = resource.credentials
        	val credentialObj = credList.map(credent => {
        	  DomainCredential(credent.handle,credent.password,credent.handle_type)
        	})
        	
        	val persDetails = resource.person_details
        	val personDomainDetails = DomainPersonDetails(persDetails.primary_user,persDetails.names.map(prNames => {
        					DomainName( prNames.name_type,
										prNames.title,
										prNames.given,
										prNames.family,
										prNames.middle,
										prNames.suffix,
										prNames.patronymic_name,
										prNames.orthography)
        					}  
        				),
	        			persDetails.addresses.map(prAddress => {
	        				DomainAddress(prAddress.address_type,
										prAddress.line1,
										prAddress.line2,
										prAddress.city,
										prAddress.county,
										prAddress.state ,
										prAddress.country_code,
										prAddress.postal_code)
	        				}
	        			)
        			)
        	
            val addressObject = DomainAddress("testUpdated","testUpdated","testUpdated","testUpdated","testUpdated","testUpdated","testUpdated","testUpdated")
            val phoneObject = DomainPhone("testUpdated","testUpdated","testUpdated","testUpdated")
            val dobObject = DomainDateOfBirth("testUpdated","testUpdated")
            
     
          
            val user =  User(Option.empty,
                resource.user_type,
                resource.language_code,
                credentialObj,
                personDomainDetails)
                
            userService.updateUser(user)
            NoContent
        })
    }
    
    def findUserById(id: Long) = Action {
        implicit val writes = Json.writes[User]
        val user = userService.tryFindById(id)
        if (user.isDefined) {
            Ok(Json.toJson(user))
        } else {
            NotFound
        }
    }
    
    def deleteUser(id: Long) = Action {
        userService.delete(id)
        NoContent
    }
    
    private def unmarshalUserResource(request: Request[JsValue], block: (PersonUser) => Result): Result = {
        request.body.validate[PersonUser].fold(
            valid = block,
            invalid = (e => {
                val error = e.mkString
                Logger.error(error)
                BadRequest(error)
            })
        )
    }

}

    