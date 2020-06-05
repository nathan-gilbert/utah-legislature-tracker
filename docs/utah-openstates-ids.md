# OpenStates IDs

* Utah House: ocd-organization/c7625e7e-dc12-49cf-aa73-fa96c5bf4b11
* Utah Senate: ocd-organization/98cb21d7-f3ae-416b-93e7-df3da962aff9
* Utah Gov: ocd-organization/75532e47-e012-442d-b2de-f650c124c0b9

How to find these values:

```graphql
{
  jurisdiction(name: "Utah") {
    name
    url
    organizations(first: 100) {
      edges {
        node {
          id
          name
          classification
          children(first: 20) {
            edges {
              node {
                name
              }
            }
          }
        }
      }
    }
  }
}
```
