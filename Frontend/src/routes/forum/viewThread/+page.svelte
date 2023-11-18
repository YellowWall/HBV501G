<script>
    import {goto,} from '$app/navigation';
    import {page} from '$app/stores';
    import {onMount} from 'svelte';
	import DisplayPost from '../../../components/DisplayPost.svelte';
	import PostForm from '../../../components/PostForm.svelte';
    

    let backendRoute = 'http://localhost:8080/forum/post?postid=';
    let props;
    async function fetchPosts(){
        const urlParams = new URLSearchParams($page.url.search);
        const id = urlParams.get('id');
        const res = await fetch(
            backendRoute + id,
            {
                method: 'GET',
                headers: {"Content-Type": "application/json"}
            }
        )
        props= {ppid:id};
        const json = await res.json();
        console.log(json);
        return json;
    }
    let dispPost = fetchPosts();
    

    
</script>

<h1>FORUM</h1>
{#await dispPost}
    <h2>Fetching post</h2>
{:then dispPost}
    <ul>
    {#each dispPost.data as post}
    <li>
         <svelte:component this={DisplayPost} {...post}/>
    </li>
    {/each}
    </ul>
    <svelte:component this={PostForm} {...props}/>
{/await}