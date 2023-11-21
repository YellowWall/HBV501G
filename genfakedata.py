import requests as rs
import random
import datetime
from pprint import pprint

BACKEND_HOST = "http://localhost:8080"
HEADERS = {
    "Content-Type": "application/json; charset=utf-8",
    "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwibmFtZSI6IlRlc3QgVXNlciIsImV4cCI6MTcwMDU2ODkxN30.iF0QaaO6-Vi-uXDP7oExu5R5pO8nb7AqGgN9bHZPZ7o"
}

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

def createGamesForUser(user, fields):
    games = []

    for n in range(5):
        field = fields[random.randint(0, len(fields) - 1)]
        date = datetime.datetime.now()
        datestr = f"{date.day}-{date.month}-{date.year}"
        games.append({ "fieldId": field["id"], "playerId": user["id"], "gameDate": datestr, "score": 0 })

    return games

def createHolesForUserAndGame(user, game, fields):
    holes = []

    field = [f for f in fields if f["id"] == game["fieldId"]][0]

    for n in range(len(field["par"])):
        par = int(field["par"][n])
        yeets = random.randint(1, par + 3)
        holes.append({ "gameId": game["id"], "playerId": user["id"], "yeets": yeets})

    return holes

def main():
    users = [
        { "username": "daniel", "password": "asdf", "name": "Daníel" },
        { "username": "ingimar", "password": "asdf", "name": "Ingimar" },
        { "username": "ivara", "password": "asdf", "name": "Ívar Á" },
        { "username": "ivars", "password": "asdf", "name": "Ívar S" }
    ]

    fields = [
        { "name": "Klambratún", "par": "334345434" },
        { "name": "Fossvogur", "par": "333433333" }
    ]

    users = createEntities("user/signup", users)
    pprint(users)
    fields = createEntities("field/postfield", fields)
    pprint(fields)

    for user in users:
        games = createGamesForUser(user, fields)
        games = createEntities("game/", games)
        pprint(games)
        for game in games:
            holes = createHolesForUserAndGame(user, game, fields)
            holes = createEntities("hole/", holes)
            pprint(holes)


if __name__ == "__main__":
    main()
