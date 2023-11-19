import requests as rs
from pprint import pprint

BACKEND_HOST = "http://localhost:8080"
HEADERS = {"Content-Type": "application/json; charset=utf-8"}

def createEntities(endpoint, entities):
    newEntities = []

    for entity in entities:
        pprint(entity)
        res = rs.post(f"{BACKEND_HOST}/{endpoint}", json=entity, headers=HEADERS)
        pprint(res)

        if res.status_code != 200:
            print(f"User {entity['name']} not created!")
            continue

        
        newEntities.append(res.json()["data"])
    
    return newEntities


def main():
    users = [
        { "username": "daniel", "password": "asdf", "name": "Daníel" },
        { "username": "ingimar", "password": "sdfa", "name": "Ingimar" },
        { "username": "ivara", "password": "dfas", "name": "Ívar Á" },
        { "username": "ivars", "password": "fasd", "name": "Ívar S" }
    ]

    fields = [
        { "name": "Klambratún", "par": "334345434" },
        { "name": "Fossvogur", "par": "333433333" }
    ]

    games = []

    users = createEntities("user/signup", users)
    pprint(users)
    


if __name__ == "__main__":
    main()
