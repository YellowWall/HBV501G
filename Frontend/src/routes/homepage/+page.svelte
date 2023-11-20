
<script>
        let backendRoute = 'http://localhost:8080';

        //console.log(Username);
        //console.log(window.sessionStorage.getItem("authenticatorTocen"));
        async function loadUser(){
            const res = await fetch(
                backendRoute + '/user/user',
                {method: 'POST',
                headers: {"Content-Type": "application/json",
                "Authorization": "Bearer " + window.sessionStorage.getItem('authenticatorTocen')},
                body: JSON.stringify({
                    username: window.sessionStorage.getItem('Username'),
                    password: '',
                    salt: '',
                    name: ''
                    })
                }
            )
            //console.log('her er res');
            //console.log(res);
            const data = await res.json();
            console.log('her er data');
            console.log(data);
            if (res.ok) {
                return data;
            }
        }

        //console.log("her er user")
        //console.log(user.data);
</script>


<h1>This is the user homepage</h1>
{#await loadUser()}
    <p>Fetching your info</p>
{:then user} 
    <p>{user.data.username}</p>
{/await}

<a href="./newgame">
<p>new game</p></a>