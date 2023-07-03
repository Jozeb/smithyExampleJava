$version: "2.0"
namespace com.software.crafters

use aws.protocols#restJson1


@restJson1
service Weather {
    version: "2020-04-02"
    operations: [
        GetCity
    ]
}


@http(method: "POST", uri: "/v1/city")
operation GetCity {
    input: GetCityInput
    output: GetCityOutput
    errors: [NoSuchResource]
}

@error("client")
@httpError(400)
structure NoSuchResource {
    @required
    resourceType: String
}

@input
structure GetCityInput {
    // "cityId" provides the identifier for the resource and
    // has to be marked as required.
    @required
    cityId: String
}

@output
structure GetCityOutput {
    // "required" is used on output to indicate if the service
    // will always provide a value for the member.
    @required
    name: String
}
