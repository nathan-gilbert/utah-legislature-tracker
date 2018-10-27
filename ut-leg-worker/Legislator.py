
import graphene

class Node(graphene.ObjectType):
    name = graphene.String()

class Edge(graphene.ObjectType):
    node = graphene.ObjectType

class People(graphene.ObjectType):
    edges = graphene.List(Edge)

class Query(graphene.ObjectType):
    people = graphene.ObjectType

query2 = """
{
    people(memberOf:"ocd-organization/c7625e7e-dc12-49cf-aa73-fa96c5bf4b11", first: 100) {
        edges {
            node {
                name
            }
        }
    }
}
"""

query1 = """
{
    people(memberOf:"ocd-organization/c7625e7e-dc12-49cf-aa73-fa96c5bf4b11", first: 100) {
        edges {
            node {
                name
                id
                party: currentMemberships(classification:"party") {
                    organization {
                        name
                    }
                }
                seat: currentMemberships(classification:"lower") {
                    post {
                        id
                        label
                        startDate
                        endDate
                    }
                }
                extras
            }
        }
    }
}
"""

if __name__ == "__main__":
    schema = graphene.Schema(query=Query)
    result = schema.execute(query2)
    print(result.data)
