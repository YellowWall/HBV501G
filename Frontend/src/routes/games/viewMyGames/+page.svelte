<script>
    import {browser} from '$app/environment';
	import Header from '../../../components/Header.svelte';
	import DisplayGame from '../../../components/DisplayGame.svelte';

    let backendRoute = 'https://hbv501g-backend.onrender.com/game/displaygames/user/';

    async function fetchGames(){
    
        const res = await fetch(
            backendRoute+window.sessionStorage.getItem('Username'),
            {
                method: 'GET',
                headers: {"Content-Type": "application/json",
                "Authorization": "Bearer " + window.sessionStorage.getItem('authenticatorTocen')}
            }
        )
        const json = await res.json();
        console.log(json);
        if(json.success){
            return json.data;
        }else return json.data;
    }
</script>
<main>
    <Header/>
    <h2>Your games</h2>
    {#await fetchGames()}
        <p>loading</p>
    {:then games}
    {#each games as game}
    <svelte:component this={DisplayGame} {...game}/>
    {/each}
    {:catch Error}
        <p style="color:red">{Error.message}</p>
    {/await}
</main>