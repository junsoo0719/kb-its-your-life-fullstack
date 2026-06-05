<template>
  <li><input type="checkbox" :checked="checked" />{{ name }}</li>
</template>

<script>
export default {
  name: 'CheckboxItem',
  data() {
    return {
      name: 'CheckboxItem',
      props: {
        id: [Number, String],
        name: String,
        checked: {
          type: Boolean,
          required: true,
          default: false,
        },
      },
    };
  },
};
</script>
