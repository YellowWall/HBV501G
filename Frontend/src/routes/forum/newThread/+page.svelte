<script>
	import Header from "../../../components/Header.svelte";
import PostForm from "../../../components/PostForm.svelte";

    let component;
    
    let props = {ppid:0};

</script>
<Header/>
<section>
    <svelte:component this={PostForm} {...props}/>
</section>