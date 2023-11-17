<script>
    import {goto,} from '$app/navigation';
    import {page} from '$app/stores';
    import {onMount} from 'svelte';
    

    let backendRoute = 'http://localhost:8080/forum/top';
    async function fetchPosts(){
        let disptitle = "no title";
        let disptext = "no text";
        let dispuser = "no user";
        let datagot = "no data got";
        const urlParams = new URLSearchParams($page.url.search);
        const res = await fetch(
            backendRoute,
            {
                method: 'GET',
                headers: {"Content-Type": "application/json"}
            }
        )
        const json = await res.json();
        datagot = json.message;
        if(json.success){
            let forumPosts = json.data;
            datagot = "some data got"

            if(forumPosts.length >0){
                disptitle = forumPosts[0].title;
                disptext = forumPosts[0].text;
            }
        }
        return {disptitle,disptext,datagot}
    }
    let dispPost = fetchPosts();
    

    
</script>

<h1>FORUM</h1>
{#await dispPost}
    <h2>Fetching post</h2>
{:then dispPost}
    <h2>{dispPost.disptitle}</h2>
    <p>{dispPost.disptext}</p>
    <p>{dispPost.datagot}</p>
    <p>Data should be fetched</p>
{/await}