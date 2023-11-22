
<script>
        import {browser} from '$app/environment';
        import {goto} from '$app/navigation';
        import NewGameForm from '../../components/NewGameForm.svelte';
        let backendRoute = 'https://hbv501g-backend.onrender.com';
        let newgame =false;
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
        function logOut(){
            window.sessionStorage.removeItem('authenticatorTocen');
            window.sessionStorage.removeItem('Username');
            goto('/');
        }

        //console.log("her er user")
        //console.log(user.data);
</script>


<h1>This is the user homepage</h1>
{#await loadUser()}
    <p>Fetching your info</p>
{:then user} 
    <p>{user.data.username}</p>
    <button on:click={()=>logOut()}>log out</button>
    <br/>
{/await}

{#if !newgame}
<button on:click={()=>{newgame = true}}>New Game</button>
{:else}
<NewGameForm/>
{/if}
<a href="./forum">
<p>Forum</p></a>
<a href="./viewallgames">
<p>sjá alla leiki</p></a>
<a href="./games/viewMyGames">
<p>Sjá leiki skráða á þig</p></a>
