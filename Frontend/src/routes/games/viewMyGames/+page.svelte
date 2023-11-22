<script>
    import {browser} from '$app/environment';
	import DisplayGame from '../../components/DisplayGame.svelte';

    let backendRoute = 'http://localhost:8080/game/displaygames/user/';

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
        return json.data;
    }
</script>
<main>
    {#await fetchGames()}
        <p>loading</p>
    {:then games}
    {#each games as game}
    <svelte:component this={DisplayGame} {...game}/>
    {/each}
    {/await}
</main>