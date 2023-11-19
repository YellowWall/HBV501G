<script>
    import {goto,} from '$app/navigation';
    import {page} from '$app/stores';
    import {onMount} from 'svelte';
    import {browser} from '$app/environment';
    import PostForm from '../../components/PostForm.svelte';
	import DisplayPost from '../../components/DisplayPost.svelte';
    
    let props = {ppid:"0"};
    let loggedIn = false;
    let token;
    if(browser){
        token = window.sessionStorage.getItem('authenticatorTocen');
        if(window.sessionStorage.getItem('Username') !=null) loggedIn = true; 
    }
    let backendRoute = 'http://localhost:8080/forum/top';
    async function fetchPosts(){
        const urlParams = new URLSearchParams($page.url.search);
        const res = await fetch(
            backendRoute,
            {
                method: 'GET',
                headers: {"Content-Type": "application/json",
                'Authorization': `Bearer ${token}`}
            }
        )
        const json = await res.json();
        console.log(json);
        return json;
    }
    

    
</script>

<h1>FORUM</h1>
{#await fetchPosts()}
    <h2>Fetching post</h2>
{:then dispPost}
    <ul>
    {#each dispPost.data as post}
    <li>
         <svelte:component this={DisplayPost} {...post}/>
    </li>
    {/each}
    </ul>
    {#if loggedIn}
        <svelte:component this={PostForm} {...props}/>
    {/if}
{/await}