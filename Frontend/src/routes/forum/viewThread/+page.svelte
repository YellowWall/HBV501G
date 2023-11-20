<script>
    import {invalidateAll,goto,} from '$app/navigation';
    import {page} from '$app/stores';
    import {onMount} from 'svelte';
    import {browser} from '$app/environment';
	import DisplayPost from '../../../components/DisplayPost.svelte';
	import PostForm from '../../../components/PostForm.svelte';
	import Header from '../../../components/Header.svelte';
    

    let backendRoute = 'http://localhost:8080/forum/post?postid=';
    let props;
    let loggedIn = false;
    let username = ""
    let token = "";
    if(browser){
        username = window.sessionStorage.getItem('Username');
        if(username !=null) loggedIn = true; 
    }
    async function fetchPosts(){
        if(browser){
            token = window.sessionStorage.getItem('authenticatorTocen');
        }
        const urlParams = new URLSearchParams($page.url.search);
        const id = urlParams.get('id');
        const res = await fetch(
            backendRoute + id,
            {
                method: 'GET',
                headers: {"Content-Type": "application/json",
                "Authorization":"Bearer "+token}
                
            }
        )
        props= {ppid:id};
        const json = await res.json();
        console.log(json);
        if(json.data?.[0].title!=null){
            props = {title:"RE: "+json.data[0].title,ppid:json.data[0].id,};
        }
        return json;
    }
    
    async function delPost(id){
        if(browser){
            token = window.sessionStorage.getItem('authenticatorTocen');
        }
        const res = await fetch(
            "http://localhost:8080/forum/deletePost",
            {
                method:'DELETE',
                headers:{"Content-Type": "application/json",
                "Authorization":"Bearer "+token},
                body: JSON.stringify({id})
            }
        )
        invalidateAll();
        goto(window.location.pathname);
    }


    
</script>
<Header></Header>
<h1>FORUM</h1>
{#await fetchPosts()}
    <h2>Fetching post</h2>
{:then dispPost}
    <ul>
    {#each dispPost.data as post}
    <li>
         <svelte:component this={DisplayPost} {...post}/>
         {#if post.username == username}
            <button on:click={()=>delPost(post.id)}>delete post</button> <button on:click={()=> goto(`./editPost?id=${post.id}`)}>Edit Post</button>
            
         {/if}
    </li>
    {/each}
    </ul>
    {#if loggedIn}
        <h2>New Reply</h2>
        <svelte:component this={PostForm} {...props}/>
    {/if}
{/await}